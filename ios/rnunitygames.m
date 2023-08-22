//
//  rnunitygames.m
//  RNUnity
//
//  Created by MAC on 22/08/23.
//

#import <Foundation/Foundation.h>
#import "React/RCTBridgeModule.h"
#import "React/RCTEventEmitter.h"
#import <React/RCTLog.h>

@interface RCT_EXTERN_MODULE(rnunitygames, RCTEventEmitter)
RCT_EXTERN_METHOD(openUnity)
RCT_EXTERN_METHOD(closeUnity)
RCT_EXTERN_METHOD(sendDataToUnity:(NSString *)value)

@end

