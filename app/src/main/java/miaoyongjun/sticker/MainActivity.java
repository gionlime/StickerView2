package miaoyongjun.sticker;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import miaoyongjun.stickerview.StickerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView cleanTv, nextTv;
    StickerView stickerView;
    //icon link http://www.easyicon.net/iconsearch/iconset:Landscapes-icons/
    int icons[] = new int[]{
            R.drawable.icon_0, R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3
            , R.drawable.icon_4, R.drawable.icon_5, R.drawable.icon_6, R.drawable.icon_7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        cleanTv = (TextView) findViewById(R.id.cleanTv);
        nextTv = (TextView) findViewById(R.id.nextTv);
        stickerView = (StickerView) findViewById(R.id.stickerView);
        stickerView.setMinStickerSizeScale(0.9f);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ImageAdapter imageAdapter = new ImageAdapter();
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                stickerView.addSticker(icons[position]);
            }
        });
        cleanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stickerView.clearSticker();
            }
        });
        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapUtil.FINAL_BITMAP = stickerView.saveSticker();
                Intent intent = new Intent(MainActivity.this, PictureActivity.class);
                startActivity(intent);
            }
        });
    }

    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements View.OnClickListener {

        private OnRecyclerViewItemClickListener mOnItemClickListener = null;

        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        public ImageAdapter() {
        }

        //?????????View??????LayoutManager?????????
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            view.setOnClickListener(this);
            return vh;
        }

        //???????????????????????????????????????
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.imageView.setImageResource(icons[position]);
            viewHolder.itemView.setTag(position);
        }

        //?????????????????????
        @Override
        public int getItemCount() {
            return icons.length;
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, (int) v.getTag());
            }
        }


        //????????????ViewHolder???????????????Item????????????????????????
        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;

            public ViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imageView);
            }
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}
