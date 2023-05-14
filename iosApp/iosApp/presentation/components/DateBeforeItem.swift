//
//  DateBeforeItem.swift
//  iosApp
//
//  Created by Paul on 28/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DateBeforeItem: View {
    var body: some View {
        ZStack {
            Rectangle()
                .frame(width: 40, height: 40)
                .cornerRadius(12)
                .foregroundColor(Color.surfaceColor)
            Image("before_month").opacity(0.3)
        }
    }
}

struct DateBeforeItem_Previews: PreviewProvider {
    static var previews: some View {
        DateBeforeItem()
    }
}
