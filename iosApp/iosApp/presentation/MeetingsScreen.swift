//
//  MeetingsScreen.swift
//  iosApp
//
//  Created by Paul on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MeetingsScreen: View {
    private var meetingsDataSource: MeetingsDataSource
    private var generateDates: GenerateDates
    private var findFirstDayOfMonthPosition: FindFirstDayOfMonthPosition
    @ObservedObject var viewModel: IOSMeetingsViewModel
    
    init(
        meetingsDataSource: MeetingsDataSource,
        generateDates: GenerateDates,
        findFirstDayOfMonthPosition: FindFirstDayOfMonthPosition
    ) {
        self.meetingsDataSource = meetingsDataSource
        self.generateDates = generateDates
        self.findFirstDayOfMonthPosition = findFirstDayOfMonthPosition
        self.viewModel = IOSMeetingsViewModel(
            meetingsDataSource: meetingsDataSource,
            generateDates: generateDates,
            findFirstDayOfMonthPosition: findFirstDayOfMonthPosition
        )
    }
    
    var body: some View {
        let screenRect = UIScreen.main.bounds
        let screenWidth = screenRect.size.width
        let padding = (screenWidth - 360 - 32) / 2
        
        VStack {
            HStack {
                HelperButton(
                    onClick: {},
                    imageName: "gearshape"
                )
                Spacer()
                DateItem(text: viewModel.state.daysLeftText)
                Spacer()
                HelperButton(
                    onClick: { viewModel.onEvent(event: MeetingsEvent.ToggleEditingMode()) },
                    imageName: viewModel.state.isInEditMode ? "checkmark" : "plus"
                )
            }
            .padding(.top, 16)
            .padding(.bottom, 20)
            .padding(.leading, padding)
            .padding(.trailing, padding)
            MonthItem(
                isEditing: viewModel.state.isInEditMode,
                isCurrent: true,
                month: viewModel.state.firstMonth.name,
                year: String(viewModel.state.firstYear),
                firstDayPosition: Int(viewModel.state.firstMonthFirstDayOfWeekPosition),
                emptyDatesAmount: Int(viewModel.state.firstMonthEmptyDatesAmount),
                dates: viewModel.state.firstMonthDates,
                onDateTap: { date in viewModel.onEvent(event: MeetingsEvent.OnDateTap(appDate: date)) }
            )
            .padding(.bottom, 16)
            MonthItem(
                isEditing: viewModel.state.isInEditMode,
                isCurrent: false,
                month: viewModel.state.secondMonth.name,
                year: String(viewModel.state.secondYear),
                firstDayPosition: Int(viewModel.state.secondMonthFirstDayOfWeekPosition),
                emptyDatesAmount: Int(viewModel.state.secondMonthEmptyDatesAmount),
                dates: viewModel.state.secondMonthDates,
                onDateTap: { date in viewModel.onEvent(event: MeetingsEvent.OnDateTap(appDate: date)) }
            )
            Spacer()
        }
        .padding(.top, 1)
        .padding(.trailing, 16)
        .padding(.leading, 16)
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

