package com.chau.phimplus.ui.movie_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chau.phimplus.R;
import com.chau.phimplus.ui.movie_detail.comment.CommentAdapter;
import com.chau.phimplus.ui.movie_detail.comment.models.Comment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FragmentBinhLuan extends Fragment implements CommentAdapter.OnCommentListener {

    // Add RecyclerView member
    private RecyclerView recyclerView;
    View mRootview;
    ArrayList<Comment> listComments = new ArrayList<Comment>();
    TextView txt_more,txt_content;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootview = inflater.inflate(R.layout.fragment_binhluan,container,false);

        // Add the following lines to create RecyclerView
        recyclerView = (RecyclerView) mRootview.findViewById(R.id.recylerview_comment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setLayoutManager(new LinearLayoutManager(mRootview.getContext()));

        //Add data
        listComments.add(new Comment(1,1,this.getString(R.string.test_long),1));
        listComments.add(new Comment(2,1,"",2));
        listComments.add(new Comment(3,1,"Content 3",3));
        listComments.add(new Comment(4,1,"Content 4",4));
        listComments.add(new Comment(5,1,"Content 5",5));
        listComments.add(new Comment(6,1,"Content 6",1));
        listComments.add(new Comment(1,1,this.getString(R.string.test_long),1));
        listComments.add(new Comment(2,1,"",2));
        listComments.add(new Comment(3,1,"Content 3",3));
        listComments.add(new Comment(4,1,"Content 4",4));
        listComments.add(new Comment(5,1,"Content 5",5));
        listComments.add(new Comment(6,1,"Content 6",1));
        listComments.add(new Comment(1,1,this.getString(R.string.test_long),1));
        listComments.add(new Comment(2,1,"",2));
        listComments.add(new Comment(3,1,"Content 3",3));
        listComments.add(new Comment(4,1,"Content 4",4));
        listComments.add(new Comment(5,1,"Content 5",5));
        listComments.add(new Comment(6,1,"Content 6",1));

        // Create adapter passing in the sample user data
        CommentAdapter commentAdapter = new CommentAdapter(getContext(),listComments,this);

        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(commentAdapter);

        // finally
        return mRootview;
    }

    @Override
    public void OnCommentClick(int position) {
//        listComments.get(position);

    }

}
