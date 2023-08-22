//
//  rnunitygames.swift
//  RNUnity
//
//  Created by MAC on 22/08/23.
//

import Foundation
@objc(rnunitygames)
class rnunitygames:RCTEventEmitter{
  
  
  // Register delegate
  override init() {
    super.init()
    NSClassFromString("FrameworkLibAPI")?.registerAPIforNativeCalls(self)
    
  }
  
  // Open unity view
  @objc func openUnity() {
    DispatchQueue.main.async {
      UnityEmbeddedSwift.showUnity()
    }
  }
  
  // Close unity
  @objc func closeUnity() {
    DispatchQueue.main.async {
      UnityEmbeddedSwift.hideUnity()
    }
  }
  
  // Receive data from react native.
  @objc func sendDataToUnity(_ value : String)  {
    
    DispatchQueue.main.async {
      UnityEmbeddedSwift.showUnity()
      // Send data to unity
      UnityEmbeddedSwift.sendUnityMessage("GameManager", methodName: "CallBackMethod", message: value)
      print("Send data to unity : \(value)")
    }
  }
  
  @objc func openURLSchemes(urlSchema:[String]) -> [String]{
    for i in urlSchema{
      if UIApplication.shared.canOpenURL(URL(string: i)!) {
        UnityEmbeddedSwift.applicationList.append(i)
      }else{
        print("Not Install")
      }
    }
    return urlSchema
  }
  
  // Support Event
  override func supportedEvents() -> [String]! {
    return ["onRNEvent"]
  }
}

extension rnunitygames:NativeCallsProtocol{
  func sendMessage(toMobileApp message: String!) {
    sendEvent(withName: "onRNEvent", body:message)
    UnityEmbeddedSwift.unloadUnity()
    
    var supportedInterfaceOrientations: UIInterfaceOrientationMask {
          return .portrait
        }
  }
}

