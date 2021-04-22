package zaid_ali.example.ratar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class PostAdapter extends ArrayAdapter<Posts> {
//    private int id;
//    private String title;
//    private String body;
    int resourceId;
    public PostAdapter(Context context,int textViewResourceId,
                       List<Posts> objects) {
        super(context, textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Posts post = getItem(position);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView title = (TextView) view.findViewById(R.id.post_title);
        TextView body = (TextView) view.findViewById(R.id.post_description);
        body.setText(post.getBody());
        title.setText(post.getTitle());
        Button btn=(Button) view.findViewById(R.id.joinchannel_id);
        btn.setText("Channel Id: "+post.getid());
//         Button bids= (Button) convertView.findViewById(R.id.postForumBtn);
//         bids.setTag(position);

//        bids.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position=(Integer) view.getTag();
//                Posts p=getItem(position);
//                Toast.makeText(getContext(), "yes !", Toast.LENGTH_SHORT).show();
//            }
//        });



        return view;
    }
}
