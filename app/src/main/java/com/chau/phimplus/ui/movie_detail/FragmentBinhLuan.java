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
import com.chau.phimplus.Server.APIserver;
import com.chau.phimplus.Server.Dataserver;
import com.chau.phimplus.ui.movie_detail.comment.CommentAdapter;
import com.chau.phimplus.ui.movie_detail.comment.models.Comment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


        getComment(2);

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

    public ArrayList<Comment> getComment(int movieId){

        Dataserver dataserver = APIserver.getServer();
        Call<ArrayList<Comment>> callback = dataserver.getComment("2");
        callback.enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                Log.d("fff", "onResponse: "+response.body().size());
                listComments = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {

            }
        });


        return null;
    }

}
