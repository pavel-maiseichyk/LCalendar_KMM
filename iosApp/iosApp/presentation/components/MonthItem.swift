//
//  MonthItem.swift
//  iosApp
//
//  Created by Paul on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MonthItem: View {
    let isEditing: Bool
    let isCurrent: Bool
    let month: String
    let year: String
    let firstDayPosition: Int
    let emptyDatesAmount: Int
    let dates: [AppDate]
    let onDateTap: (AppDate) -> Void
    
    let columns: [GridItem] = [
        GridItem(.fixed(40), spacing: nil, alignment: nil),
        GridItem(.fixed(40), spacing: nil, alignment: nil),
        GridItem(.fixed(40), spacing: nil, alignment: nil),
        GridItem(.fixed(40), spacing: nil, alignment: nil),
        GridItem(.fixed(40), spacing: nil, alignment: nil),
        GridItem(.fixed(40), spacing: nil, alignment: nil),
        GridItem(.fixed(40), spacing: nil, alignment: nil)
    ]
    
    var body: some View {
        VStack {
            HStack {
                Text(month)
                    .font(.custom("Montserrat-Regular", size: 28))
                Spacer()
                Text(year)
                    .font(.custom("Montserrat-Regular", size: 28))
            }
            .padding(16)
            
            LazyVGrid(
                columns: columns,
                alignment: .center,
                spacing: 8
            ) {
                ForEach(0..<firstDayPosition, id: \.self) { _ in
                    if isCurrent {
                        DateBeforeItem()
                    } else {
                        DateAfterItem()
                    }
                }
                ForEach(dates, id: \.self.date) { date in
                    Dayitem(
                        onClick: { onDateTap(date) },
                        text: String(date.date.dayOfMonth),
                        type: date.type,
                        isClickable: isEditing
                    )
                }
                ForEach(0..<emptyDatesAmount, id: \.self) { _ in
                    DateAfterItem()
                }
            }
            .padding(.bottom, 16)
        }
        .frame(width: 360)
        .background(Color.surfaceColor)
        .cornerRadius(24)
        .overlay(
            RoundedRectangle(cornerRadius: 24)
                  .stroke(.black, lineWidth: 1)
        )
    }
}

let dates = [
    AppDate(date: Kotlinx_datetimeLocalDate(year: 2023, month: Kotlinx_datetimeMonth.december, dayOfMonth: 1), type: DateType.normal),
    AppDate(date: Kotlinx_datetimeLocalDate(year: 2023, month: Kotlinx_datetimeMonth.december, dayOfMonth: 2), type: DateType.normal),
    AppDate(date: Kotlinx_datetimeLocalDate(year: 2023, month: Kotlinx_datetimeMonth.december, dayOfMonth: 3), type: DateType.normal),
    AppDate(date: Kotlinx_datetimeLocalDate(year: 2023, month: Kotlinx_datetimeMonth.december, dayOfMonth: 4), type: DateType.normal),
    AppDate(date: Kotlinx_datetimeLocalDate(year: 2023, month: Kotlinx_datetimeMonth.december, dayOfMonth: 5), type: DateType.normal),
    AppDate(date: Kotlinx_datetimeLocalDate(year: 2023, month: Kotlinx_datetimeMonth.december, dayOfMonth: 6), type: DateType.normal),
    AppDate(date: Kotlinx_datetimeLocalDate(year: 2023, month: Kotlinx_datetimeMonth.december, dayOfMonth: 7), type: DateType.normal),
    AppDate(date: Kotlinx_datetimeLocalDate(year: 2023, month: Kotlinx_datetimeMonth.december, dayOfMonth: 8), type: DateType.normal),
    AppDate(date: Kotlinx_datetimeLocalDate(year: 2023, month: Kotlinx_datetimeMonth.december, dayOfMonth: 9), type: DateType.normal)
]

struct MonthItem_Previews: PreviewProvider {
    static var previews: some View {
        MonthItem(
            isEditing: false,
            isCurrent: true,
            month: "December",
            year: "2023",
            firstDayPosition: 4,
            emptyDatesAmount: 0,
            dates: dates,
            onDateTap: { date in }
        )
    }
}
