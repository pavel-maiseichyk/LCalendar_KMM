//
//  WigglingModifier.swift
//  iosApp
//
//  Created by Paul on 03/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension View {
    func wiggling(isActive: Bool) -> some View {
        modifier(WiggleModifier(isActive: isActive))
    }
}

struct WiggleModifier: ViewModifier {
    @State private var isWiggling = false
    let isActive: Bool
    
    private static func randomize(interval: TimeInterval, withVariance variance: Double) -> TimeInterval {
        let random = (Double(arc4random_uniform(1000)) - 500.0) / 500.0
        return interval + variance * random
    }
    
    private let rotateAnimation = Animation
        .easeInOut(
            duration: WiggleModifier.randomize(
                interval: 0.14,
                withVariance: 0.025
            )
        )
        .repeatForever(autoreverses: true)
    
    private let bounceAnimation = Animation
        .easeInOut(
            duration: WiggleModifier.randomize(
                interval: 0.18,
                withVariance: 0.025
            )
        )
        .repeatForever(autoreverses: true)
    
    func body(content: Content) -> some View {
        if isActive {
            content
                .rotationEffect(.degrees(isWiggling ? 2.0 : 0))
                .animation(rotateAnimation)
                .offset(x: 0, y: isWiggling ? 2.0 : 0)
                .animation(bounceAnimation)
                .onAppear() { isWiggling.toggle() }
        } else {
            content
        }
    }
}
