cordova-identify-user
=====================
Supports only android.


To install:
cordova plugin install https://github.com/mehernosh/cordova-identify-user.git

---

All device accounts will be available as name type pairs after device load in the window.UserIdentities object.
This information is cached for the life of the page.

---

To refresh the information, you can use:
var uids = new UserIdentities();
uids.get(successCallback, errorCallback)
