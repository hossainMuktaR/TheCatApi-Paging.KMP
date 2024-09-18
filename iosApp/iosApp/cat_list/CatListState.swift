//
//  CatListState.swift
//  iosApp
//
//  Created by Hossain Muktar on 18/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

struct CatListState {
    var isLoading: Bool = false
    var cat: [Cat] = []
    var error: String? = nil
}
