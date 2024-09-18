//
//  CatDetailScreen.swift
//  iosApp
//
//  Created by Hossain Muktar on 18/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//


import SwiftUI
import Shared

struct CatDetailScreen: View {
    var cat: Cat
    var body: some View {
        ScrollView{
            VStack {
                AsyncImage(url: URL(string: cat.url)) { image in
                    image.image?.resizable()
                }.aspectRatio(contentMode: .fill)
                
                VStack{
                    if let catBread = cat.breeds.first {
                        HStack{
                            Text("Name :")
                                .font(.headline)
                            Text(catBread.name)
                                .font(.body)
                        }
                        Divider()
                        HStack{
                            Text("Origin :")
                                .font(.headline)
                            Text(catBread.origin)
                                .font(.subheadline)
                        }
                        Divider()
                        HStack{
                            Text("Temperament :")
                                .font(.headline)
                            Text(catBread.temperament)
                                .font(.subheadline)
                        }
                        Divider()
                        VStack {
                            Text("Description")
                                .font(.title)
                            Text(catBread.description)
                                .font(.body)
                        }
                    }
                }
                .padding()
                Spacer()
                
            }
            .navigationTitle(cat.breeds.first!.name)
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

#Preview {
    CatDetailScreen(
        cat: Cat.companion.example
    )
}
