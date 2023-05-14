//
//  HelperButton.swift
//  iosApp
//
//  Created by Paul on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct HelperButton: View {
    let onClick: () -> Void
    let imageName: String
    
    var body: some View {
        Button(action: onClick) {
            Image(systemName: imageName)
                .resizable()
                .frame(width: 20, height: 20)
                .foregroundColor(Color.onColor)
                .padding(20)
        }
        .background(Color.secondaryColor)
        .cornerRadius(24)
        .overlay(
            RoundedRectangle(cornerRadius: 24)
                  .stroke(.black, lineWidth: 1)
        )
    }
}

struct HelperButton_Previews: PreviewProvider {
    static var previews: some View {
        HStack {
            HelperButton(onClick: {}, imageName: "plus")
            HelperButton(onClick: {}, imageName: "gearshape")
        }
    }
}
