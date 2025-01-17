package ja.burhanrashid52.photoeditor;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Burhanuddin Rashid on 18/05/21.
 *
 * @author <https://github.com/burhanrashid52>
 */
class BoxHelper {
    private final ViewGroup mViewGroup;
    private final PhotoEditorViewState mViewState;

    public BoxHelper(ViewGroup viewGroup, PhotoEditorViewState viewState) {
        mViewGroup = viewGroup;
        mViewState = viewState;
    }

    void clearHelperBox() {
        for (int i = 0; i < mViewGroup.getChildCount(); i++) {
            View childAt = mViewGroup.getChildAt(i);
            FrameLayout frmBorder = childAt.findViewById(R.id.frmBorder);
            if (frmBorder != null) {
                frmBorder.setBackgroundResource(0);
            }
            ImageView imgClose = childAt.findViewById(R.id.imgPhotoEditorClose);
            if (imgClose != null) {
                imgClose.setVisibility(View.GONE);
            }
        }
        mViewState.clearCurrentSelectedView();
    }

    public void clearAllViews(DrawingView drawingView) {
        for (int i = 0; i < mViewState.getAddedViewsCount(); i++) {
            mViewGroup.removeView(mViewState.getAddedView(i));
        }
        if (mViewState.containsAddedView(drawingView)) {
            mViewGroup.addView(drawingView);
        }
        mViewState.clearAddedViews();
        mViewState.clearRedoViews();

        if (drawingView != null)
            drawingView.clearAll();
    }

    public void invalidateAllViews(View drawingView) {
        ViewGroup myViewGroup = ((ViewGroup) drawingView.getParent());
        for (int i = 0; i < myViewGroup.getChildCount(); i++) {
            myViewGroup.getChildAt(i).invalidate();
        }
    }

    public void moveToBack(View drawingView)
    {
        ViewGroup myViewGroup = ((ViewGroup) drawingView.getParent());
        int length = myViewGroup.getChildCount();
        for(int i = 1; i<length; i++)
        {
            myViewGroup.getChildAt(i).setTranslationZ(0);
        }
        invalidateAllViews(drawingView);
        drawingView.setTranslationZ(-1);
        drawingView.invalidate();
    }
}
