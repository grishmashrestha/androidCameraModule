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
    String dataset[] = null;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

//        set up recycler view
        fetchImages();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AlbumRecyclerViewAdapter(dataset, GalleryActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void fetchImages() {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        dataset = dir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (new File(dir, name).isDirectory())
                    return false;
                return name.toLowerCase().endsWith(".jpg");
            }
        });
    }

}
