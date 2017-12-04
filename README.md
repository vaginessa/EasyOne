# EasyOne

![ScreenShot](https://i.imgur.com/gdnA3fM.png)


Simple overlay that adds a bar on the left side of the screen (START). You can use it to open other s#@$s and do the s#@$ you want (for example by implementing click, long click or gestures on the bar).

There is also a intro asking for permissions. The intro is the main activity dynamically changed on permission request result. When all the permissions are satisfied the OverlayService is started.
You can change the permissions system as You want. In MainActivity there's a method called "switchPermission(int code)". Here You can change the drawable, the permission rationale according to the permission request:

``` java

//determine the code request
final boolean phone = code != PermissionUtils.CONTACT_REQUEST_CODE;

//determine the drawable to be used according to the code
int drawable = phone ? R.drawable.ic_phone : R.drawable.ic_contact;

//change the activity's background color
int bg_color = phone ? R.color.colorPhone : R.color.colorContact;

//change the status bar color
int sb_color = phone ? R.color.colorPhoneDark : R.color.colorContactDark;

//this is the permission rationale
String text = phone ? getString(R.string.call_permission) : getString(R.string.contact_perm);

//a text view with a drawable on the top
rationale.setCompoundDrawablesWithIntrinsicBounds(0, drawable, 0, 0);

//set the selected colors
getWindow().getDecorView().getRootView().setBackgroundColor(getResources().getColor(bg_color, getTheme()));
getWindow().setStatusBarColor(getResources().getColor(sb_color, getTheme()));
getWindow().setNavigationBarColor(getResources().getColor(sb_color, getTheme()));
```



The bar is customizable (e.g. using a preference activity?). Inside OverlayService:

**dragWidth** = the bar's width;

**params** = the metrics applied to the bar

WindowManager.LayoutParams params = new WindowManager.LayoutParams(**WIDTH (dragWidth)**, **HEIGHT**, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);

**params.gravity** = the gravity of the bar. Possible values: Gravity.START, GRAVITY.END;

**EXAMPLE

``` java

//this is the bar width
int dragWidth = 20;

WindowManager.LayoutParams params = new WindowManager.LayoutParams(dragWidth, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);

//change this through preferences
params.gravity = Gravity.START;
```
