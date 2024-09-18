//
//  CatImageItem.swift
//  iosApp
//
//  Created by Hossain Muktar on 18/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct CatImageItem: View {
    var cat: Cat
    var body: some View {
        HStack(alignment: .center) {
            AsyncImage(url: URL(string: cat.url)) { image in
                image.image?.resizable()
            }.aspectRatio(contentMode: .fit)
                .frame(width: 80, height: 80) // Set the size of the circle
                .clipShape(Circle()) // Make the image circular
                .overlay(Circle().stroke(Color.accentColor.opacity(0.5), lineWidth: 3))
            VStack(alignment: .leading, spacing: 0){
                if let catBread = cat.breeds.first {
                    Text(catBread.name)
                        .font(.title)
                    HStack{
                        Text(catBread.origin)
                        Spacer()

                    }
                    Spacer()
                }
                
            }
            Spacer()
            
        }
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: 80)
        
        
    }
}
#Preview {
    CatImageItem(
        cat: Cat.companion.example
    )
}
