//
//  CatListViewModel.swift
//  iosApp
//
//  Created by Hossain Muktar on 18/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

@Observable
class CatListViewModel {
    private let api: CatApiService = CatApiServiceImpl()
    private let repository: CatRepository
    private let getCatsImageUseCase: GetCatsImageUseCase
    
    var cat: [Cat] = []
    var isLoading = false
    var error: String? = nil
    
    // pagination state
    private var canLoadMorePages = true
    private var page = 1
    private var limit = 15
    
    init() {
        self.repository = CatRepositoryImpl(apiService: self.api)
        self.getCatsImageUseCase = GetCatsImageUseCase(repository: self.repository)
    }
    
    func getImages() {
        guard !isLoading && canLoadMorePages else { return }
        self.isLoading = true
        Task {
            let result = getCatsImageUseCase.execute(page: Int32(page), limit: Int32(limit))
            
            for await phrase in self.getCatsImageUseCase.execute(page: Int32(page), limit: Int32(limit)) {
                if let cat = phrase.data as? [Cat] {
                    self.cat.append(contentsOf: cat)
                    if cat.count < self.limit {
                        self.canLoadMorePages = false
                    }
                    self.page += 1
                    print("vm: getCoins: .success Called")
                    print("vm: getCoins: .success: \(self.page)")
                    self.isLoading = false
                    print("isloading = false")
                }
            }
        }
    }
}
