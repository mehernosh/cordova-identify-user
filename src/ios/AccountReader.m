/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

#include <sys/types.h>
#include <sys/sysctl.h>

#import <Cordova/CDV.h>
#import "AccountReader.h"

@implementation UIDevice (ModelVersion)

- (NSString*)modelVersion
{
    size_t size;

    sysctlbyname("hw.machine", NULL, &size, NULL, 0);
    char* machine = malloc(size);
    sysctlbyname("hw.machine", machine, &size, NULL, 0);
    NSString* platform = [NSString stringWithUTF8String:machine];
    free(machine);

    return platform;
}

@end

@interface AccountReader () {}
@end

@implementation AccountReader

- (void)getAllAccounts:(CDVInvokedUrlCommand*)command
{
    NSDictionary* accountsInfo = [self accountsInfo];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:accountsInfo];

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (NSDictionary*)accountsInfo
{
    NSMutableDictionary* accountProps = [NSMutableDictionary dictionaryWithCapacity:4];

    [accountProps setObject:[NSArray array] forKey:@"accounts"];

    NSDictionary* accReturn = [NSDictionary dictionaryWithDictionary:accountProps];
    return accReturn;
}

+ (NSString*)cordovaVersion
{
    return CDV_VERSION;
}

@end
