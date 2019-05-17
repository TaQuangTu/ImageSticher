package tan.examlple.com.javacoban.imageprocess;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.util.ArrayList;

import static tan.examlple.com.javacoban.imageprocess.ImageStitcher.*;

public class ImageProcessThread extends AsyncTask<Void,ImageView,Bitmap> {

    public interface ImageProcessingListener
    {
        void setUIAfterRun(Bitmap result);
        void setUIBeforeRun();
    }

    private ImageProcessingListener imageProcessingListener;
    private ArrayList<Bitmap> bitmapArrayList;
    private PercentageListener mPercentageListener;


    public void setDataBeforeRun(ArrayList<Bitmap> bitmapArray) {
        bitmapArrayList = bitmapArray;
    }
    public void setmPercentageListener(PercentageListener percentageListener){this.mPercentageListener = percentageListener;}

    public ImageProcessThread(ImageProcessingListener imageProcessingListener){
        this.imageProcessingListener = imageProcessingListener;
    }
    @Override
    protected void onPreExecute() {
        imageProcessingListener.setUIBeforeRun();
    }
    @Override
    protected Bitmap doInBackground(Void... voids) {
        ImageStitcher imageStitcher = getInstance();
        imageStitcher.setPercentageListener(mPercentageListener);
        //stitch two first bitmap
        Bitmap result = imageStitcher.stichMultipleBitmap(bitmapArrayList);
        return result;
    }
    @Override
    protected void onPostExecute(Bitmap result) {
        imageProcessingListener.setUIAfterRun(result);
    }
}
