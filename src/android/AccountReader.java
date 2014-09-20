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
package org.systematicsheep.IdentifyUser;

import java.util.TimeZone;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.content.Context;

import android.provider.Settings;

public class AccountReader extends CordovaPlugin {
    public static final String TAG = "AccountReader";
    AccountManager manager = null;


    /**
     * Constructor.
     */
    public AccountReader() {
    }


    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getAllAccounts")) {
            JSONObject r = new JSONObject();
            r.put("accounts", this.getAllAccounts());
            callbackContext.success(r);
        }
        else {
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------

    /**
     * Get all accounts on device.
     * 
     * @return
     */
    private JSONArray getAllAccounts() throws JSONException {
        JSONArray accountsJson = new JSONArray();
        AccountManager accountManager = AccountManager.get(cordova.getActivity());
        Account[] accounts = accountManager.getAccounts();
        for(Account account : accounts){
            JSONObject accJson = new JSONObject();
            accJson.put("name", account.name);
            accJson.put("type", account.type);   
            accountsJson.put(accJson);         
        }
        return accountsJson;
    }
}
