//
//  DateAfterItem.swift
//  iosApp
//
//  Created by Paul on 28/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DateAfterItem: View {
    var body: some View {
        Rectangle()
            .frame(width: 40, height: 40)
            .cornerRadius(12)
            .foregroundColor(Color.surfaceColor)
            .overlay(
                RoundedRectangle(cornerRadius: 12)
                    .stroke(.black, lineWidth: 1)
                    .opacity(0.3)
            )
    }
}

struct DateAfterItem_Previews: PreviewProvider {
    static var previews: some View {
        DateAfterItem()
    }
}
