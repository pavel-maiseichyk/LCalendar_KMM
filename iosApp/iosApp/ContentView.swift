import SwiftUI
import shared

struct ContentView: View {

    private let appModule = AppModule()
    
	var body: some View {
        ZStack {
            Color.backgroundColor
                .ignoresSafeArea()
            MeetingsScreen(
                meetingsDataSource: appModule.meetingsDataSource,
                generateDates: appModule.generateDatesUseCase,
                findFirstDayOfMonthPosition: appModule.findFirstDayOfMonthPositionUseCase
            )
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
 
