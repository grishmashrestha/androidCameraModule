package np.com.grishma.cameramodule;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;

public class GalleryActivity extends AppCompatActivity {
    String myDataset[] = null;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

//        set up recycler view

        fetchImages();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AlbumRecyclerViewAdapter(myDataset, GalleryActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void fetchImages() {
        Log.e("MainActivity", "test");
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        myDataset = dir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (new File(dir, name).isDirectory())
                    return false;
                return name.toLowerCase().endsWith(".jpg");
            }
        });
    }

}
