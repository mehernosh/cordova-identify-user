cordova-identify-user
=====================
Supports only android.


To install:
cordova plugin install https://github.com/mehernosh/cordova-identify-user.git

---

All device accounts will be available as name type pairs after device load in the window.UserIdentities.uids object.
This information is cached for the life of the page.

---

To refresh the information, you can use:

window.UserIdentities.get(successCallback,errorCallback)
Note that this will re-populate the window.UserIdentities.uids object. Nothing will be passed back to the callbacks
