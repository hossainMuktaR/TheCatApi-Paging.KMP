//
//  CatListScreen.swift
//  iosApp
//
//  Created by Hossain Muktar on 18/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct CatListScreen: View {
    @State var vm = CatListViewModel()
    var body: some View {
            VStack {
                
                List{
                    ForEach(vm.cat, id: \.id) { cat in
                        NavigationLink(destination: CatDetailScreen(cat: cat)) {
                            CatImageItem(cat: cat)
                        }
                    }
                    if vm.isLoading {
                        HStack {
                            Spacer()
                            Image(systemName: "circle.circle")
                            Spacer()
                        }
                    } else {
                        Color.clear
                            .onAppear {
                                vm.getImages()
                            }
                    }
                }
                    
            }
            .navigationTitle("Cat")
        
    }
}
#Preview {
    CatListScreen()
}
