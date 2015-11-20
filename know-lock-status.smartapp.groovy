/**
 *  Know Lock Status
 *
 *  Copyright 2015 Thomas Vaughn
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Know Lock Status",
    namespace: "thomasvaughn",
    author: "Thomas Vaughn",
    description: "Get notifications when a lock is locked and/or unlocked.",
    category: "Safety & Security",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    oauth: true)


preferences {  
    section("Select the door lock") {
        input "lock1", "capability.lock", multiple: true
    }
}

def installed() {
    subscribe(lock1, "lock", checkTime)
    subscribe(lock1, "unlock", checkTime)
    
    initialize()
}

def updated() {
    unsubscribe()
    initialize()
}

def initialize() {
    subscribe(lock1, "lock", checkTime)
    subscribe(unlock1, "unlock", checkTime)
}

def checkTime(evt) {
    if(evt.value == "locked") {
        sendPush("$settings.lock1 Locked")
    }
    
    if(evt.value == "unlocked") {
        sendPush("$settings.lock1 Unlocked")
    }
}

