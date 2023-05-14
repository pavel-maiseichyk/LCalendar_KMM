//
//  Dayitem.swift
//  iosApp
//
//  Created by Paul on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct Dayitem: View {
    let onClick: () -> Void
    let text: String
    let type: DateType
    let isClickable: Bool
    
    var body: some View {
        Button(action: onClick) {
            switch type {
            case DateType.normal:
                DayItemBase(
                    text: text,
                    background: .white
                )
                .wiggling(isActive: isClickable)
                .overlay(
                    RoundedRectangle(cornerRadius: 12)
                        .stroke(.black, lineWidth: 1)
                )
            case DateType.futureMeeting:
                DayItemBase(
                    text: text,
                    background: .futureMeetingColor
                )
                .wiggling(isActive: isClickable)
                .overlay(
                    RoundedRectangle(cornerRadius: 12)
                        .stroke(.black, lineWidth: 1)
                )
            case DateType.pastMeeting:
                ZStack {
                    DayItemBase(
                        text: text,
                        background: .pastMeetingColor
                    )
                    .wiggling(isActive: isClickable)
                    .overlay(
                        RoundedRectangle(cornerRadius: 12)
                            .stroke(.black, lineWidth: 1)
                    )
                    Image("before_month").opacity(0.7)
                }
            case DateType.past:
                ZStack {
                    DayItemBase(
                        text: text,
                        background: .surfaceColor
                    )
                    .wiggling(isActive: isClickable)
                    Image("before_month").opacity(0.7)
                }
            case DateType.today, DateType.todayMeeting:
                DayItemBase(
                    text: text,
                    background: .primaryColor
                )
                .wiggling(isActive: isClickable)
                .overlay(
                    RoundedRectangle(cornerRadius: 12)
                        .stroke(.black, lineWidth: 1)
                )
            case DateType.special, DateType.specialMeeting:
                DayItemBase(
                    text: text,
                    background: .specialColor
                )
                .wiggling(isActive: isClickable)
                .overlay(
                    RoundedRectangle(cornerRadius: 12)
                        .stroke(.black, lineWidth: 1)
                )
            default:
                Text(text)
                    .wiggling(isActive: isClickable)
            }
        }
        .foregroundColor(.onColor)
        .disabled(!isClickable)
    }
}

private struct DayItemBase: View {
    let text: String
    let background: Color
    
    var body: some View {
        Text(text)
            .font(.custom("Montserrat-Regular", size: 20))
            .frame(width: 40, height: 40)
            .background(background)
            .cornerRadius(12)
    }
}
//private struct DayItemBase: View {
//    let text: String
//    let background: Color
//    let hasBorder: Bool
//    let isActive: Bool
//    let isPast: Bool
//
//    var body: some View {
//        if (!isActive) {
//            if (hasBorder) {
//                Text(text)
//                    .font(.custom("Montserrat-Regular", size: 20))
//                    .frame(width: 40, height: 40)
//                    .background(background)
//                    .cornerRadius(12)
//                    .overlay(
//                        RoundedRectangle(cornerRadius: 12)
//                            .stroke(.black, lineWidth: 1)
//                    )
//            } else {
//                Text(text)
//                    .font(.custom("Montserrat-Regular", size: 20))
//                    .frame(width: 40, height: 40)
//                    .background(background)
//                    .cornerRadius(12)
//            }
//        } else {
//            if (hasBorder) {
//                Text(text)
//                    .font(.custom("Montserrat-Regular", size: 20))
//                    .frame(width: 40, height: 40)
//                    .background(background)
//                    .cornerRadius(12)
//                    .overlay(
//                        RoundedRectangle(cornerRadius: 12)
//                            .stroke(.black, lineWidth: 1)
//                    )
//                    .wiggling()
//            } else {
//                Text(text)
//                    .font(.custom("Montserrat-Regular", size: 20))
//                    .frame(width: 40, height: 40)
//                    .background(background)
//                    .cornerRadius(12)
//                    .wiggling()
//            }
//        }
//    }
//}

struct Dayitem_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
//            Dayitem(onClick: {}, text: "1", type: DateType.normal)
//            Dayitem(onClick: {}, text: "1", type: DateType.futureMeeting)
//            Dayitem(onClick: {}, text: "1", type: DateType.pastMeeting)
//            Dayitem(onClick: {}, text: "1", type: DateType.today)
//            Dayitem(onClick: {}, text: "1", type: DateType.past)
        }
    }
}
