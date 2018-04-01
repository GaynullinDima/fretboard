package org.mozilla.fretboard

import org.mozilla.fretboard.helpers.FretboardConfiguration
import org.mozilla.fretboard.net.FretboardClient
import org.mozilla.fretboard.scheduler.FretboardScheduler
import org.mozilla.fretboard.storage.FretboardExperimentsStorage

class Fretboard(
        val configuration: FretboardConfiguration, val client: FretboardClient,
        val storage: FretboardExperimentsStorage, val scheduler: FretboardScheduler
) {

}