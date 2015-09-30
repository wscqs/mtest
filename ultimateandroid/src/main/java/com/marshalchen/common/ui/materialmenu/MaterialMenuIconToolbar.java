package com.marshalchen.common.ui.materialmenu;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.marshalchen.common.ui.materialmenu.MaterialMenuBase;
import com.marshalchen.common.ui.materialmenu.MaterialMenuDrawable;


public abstract class MaterialMenuIconToolbar extends MaterialMenuBase {

    public MaterialMenuIconToolbar(Activity activity, int color, MaterialMenuDrawable.Stroke stroke) {
        super(activity, color, stroke);
    }

    public MaterialMenuIconToolbar(Activity activity, int color, MaterialMenuDrawable.Stroke stroke, int transformDuration) {
        super(activity, color, stroke, transformDuration);
    }

    public MaterialMenuIconToolbar(Activity activity, int color, MaterialMenuDrawable.Stroke stroke, int transformDuration, int pressedDuration) {
        super(activity, color, stroke, transformDuration, pressedDuration);
    }

    @Override
    protected final void setActionBarSettings(Activity activity) {
        Toolbar toolbar = (Toolbar) activity.findViewById(getToolbarViewId());
        toolbar.setNavigationIcon(getDrawable());
    }

    @Override
    protected final View getActionBarHomeView(Activity activity) {
        return null;
    }

    @Override
    protected final View getActionBarUpView(Activity activity) {
        return null;
    }

    @Override
    protected final boolean providesActionBar() {
        return false;
    }

    public abstract int getToolbarViewId();
}
