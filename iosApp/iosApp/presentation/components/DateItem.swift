//
//  DateItem.swift
//  iosApp
//
//  Created by Paul on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DateItem: View {
    let text: String
    
    var body: some View {
        Text(text.uppercased())
            .padding(.horizontal, 24)
            .padding(.vertical, 12)
            .background(Color.primaryColor)
            .cornerRadius(24)
            .font(.custom("Montserrat-Regular", size: 32))
            .overlay(
                RoundedRectangle(cornerRadius: 24)
                      .stroke(.black, lineWidth: 1)
            )
    }
}

struct DateItem_Previews: PreviewProvider {
    static var previews: some View {
        DateItem(text: "20 days")
    }
}
