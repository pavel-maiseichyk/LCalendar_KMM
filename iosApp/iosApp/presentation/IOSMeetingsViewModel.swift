//
//  IOSMeetingsViewModel.swift
//  iosApp
//
//  Created by Paul on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension MeetingsScreen {
    
    @MainActor class IOSMeetingsViewModel: ObservableObject {
        private var meetingsDataSource: MeetingsDataSource
        private var generateDates: GenerateDates
        private var findFirstDayOfMonthPosition: FindFirstDayOfMonthPosition
        
        private let viewModel: MeetingsViewModel
        
        @Published var state: MeetingsState = MeetingsState(
            firstMonth: .january,
            secondMonth: .february,
            firstYear: 0,
            secondYear: 0,
            firstMonthDates: [],
            secondMonthDates: [],
            firstMonthFirstDayOfWeekPosition: 0,
            secondMonthFirstDayOfWeekPosition: 0,
            firstMonthEmptyDatesAmount: 0,
            secondMonthEmptyDatesAmount: 0,
            isInEditMode: false,
            daysLeftText: ""
        )
        private var handle: DisposableHandle?
        
        init(
            meetingsDataSource: MeetingsDataSource,
            generateDates: GenerateDates,
            findFirstDayOfMonthPosition: FindFirstDayOfMonthPosition
        ) {
            self.meetingsDataSource = meetingsDataSource
            self.generateDates = generateDates
            self.findFirstDayOfMonthPosition = findFirstDayOfMonthPosition
            self.viewModel = MeetingsViewModel(
                meetingsDataSource: meetingsDataSource,
                generateDates: generateDates,
                findFirstDayOfMonthPosition: findFirstDayOfMonthPosition,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: MeetingsEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
