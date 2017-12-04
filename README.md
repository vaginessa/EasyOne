# EasyOne


Simple overlay that adds a bar on the left side of the screen (START). You can use it to open other s#@$s and do the s#@$ you want (for example by implementing click, long click or gestures on the bar).

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
