package com.chau.phimplus.ui.movie_detail;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chau.phimplus.R;
import com.chau.phimplus.Server.APIserver;
import com.chau.phimplus.Server.Dataserver;
import com.chau.phimplus.ui.movie_detail.LichChieu.Models.Branch;
import com.chau.phimplus.ui.movie_detail.LichChieu.Models.Cinema;
import com.chau.phimplus.ui.movie_detail.LichChieu.Models.Screening;
import com.chau.phimplus.ui.movie_detail.LichChieu.Models.Seat;
import com.chau.phimplus.ui.movie_detail.LichChieu.Models.TempData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Setup_ticket extends AppCompatActivity {

    public static ArrayList<Branch> mListBranch;
    public static ArrayList<Cinema> mListCinema;
    ArrayList<String> listBranchAddress;
    public static String thisBranchAddress;
    public TempData tempData = new TempData();

    final String MOVIE_ID = "1";

    Spinner sp_branch,sp_cinema,sp_sreening,sp_seat;
    Button btn_datVe;
    ProgressDialog nDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_ticket);

        init();
        nDialog.show();
        getBranch();
        btn_datVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Setup_ticket.this, tempData.getSreeningId()+"~~"+tempData.getCinemaId()+"~~"+tempData.getBranchAddress(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void init() {
        sp_branch = (Spinner)findViewById(R.id.spiner_branch);
        sp_branch.setSelected(true);
        sp_cinema = (Spinner)findViewById(R.id.spiner_cinema);
        sp_cinema.setSelected(true);
        sp_sreening = (Spinner)findViewById(R.id.spiner_screening);
        sp_sreening.setSelected(true);
        sp_seat = (Spinner)findViewById(R.id.spiner_seat);
        sp_seat.setSelected(true);
        btn_datVe = (Button)findViewById(R.id.button_datve);
        //dialog loading
        nDialog = new ProgressDialog(Setup_ticket.this);
        nDialog.setMessage("Loading.. ^^");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(false);
    }
    public void loadSpinnerBranch() {
        final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item, listBranchAddress);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_branch.setAdapter(adapter);
        sp_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                thisBranchAddress = listBranchAddress.get(position);
                getCinema(thisBranchAddress);
                tempData.setBranchAddress(thisBranchAddress);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void getBranch() {

        Dataserver dataserver = APIserver.getServer();
        Call<ArrayList<Branch>> callback = dataserver.getBranch(MOVIE_ID);
        callback.enqueue(new Callback<ArrayList<Branch>>() {
            @Override
            public void onResponse(Call<ArrayList<Branch>> call, Response<ArrayList<Branch>> response) {
                mListBranch = response.body();
                tempData.setListBranch(response.body());
               getAddress(); // tai sao
               loadSpinnerBranch(); // chi de dc o day
            }
            @Override
            public void onFailure(Call<ArrayList<Branch>> call, Throwable t) {
            }
        });
    }
    public void getAddress()    {
        listBranchAddress = new ArrayList<>(mListBranch.size());
        for (Branch object : mListBranch) {
            listBranchAddress.add(object.getAddress());
        }
    }
    public void getCinema(final String address) {
            Dataserver dataserver = APIserver.getServer();
            Call<ArrayList<Cinema>> callback = dataserver.getCinema(MOVIE_ID,address);
            callback.enqueue(new Callback<ArrayList<Cinema>>() {
                @Override
                public void onResponse(Call<ArrayList<Cinema>> call, Response<ArrayList<Cinema>> response) {
                    mListCinema = response.body();
                    loadSpinnerCinema(mListCinema);
                    tempData.setListCinema(response.body());
                }

                @Override
                public void onFailure(Call<ArrayList<Cinema>> call, Throwable t) {

                }
            });

    }
    public void loadSpinnerCinema(final ArrayList<Cinema> list) {
        final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item, getCinemaName(list));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_cinema.setAdapter(adapter);
        sp_cinema.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getSreening(list.get(position).getId(),MOVIE_ID);
                tempData.setCinemaId(list.get(position).getId());
                //
                getSeatList();
                //loadSpinnerSeat(getSeatName(tempData.getListSeat()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public ArrayList<String> getCinemaName(ArrayList<Cinema> list)    {
        ArrayList<String> result = new ArrayList<>(list.size());
        for (Cinema object : list) {
            result.add(object.getName());
        }
        return result;
    }
    public void getSreening(final String cinemaid,String movieId) {
        Dataserver dataserver = APIserver.getServer();
        Call<ArrayList<Screening>> callback = dataserver.getSreening(cinemaid,movieId);
        callback.enqueue(new Callback<ArrayList<Screening>>() {
            @Override
            public void onResponse(Call<ArrayList<Screening>> call, Response<ArrayList<Screening>> response) {
                loadSpinnerScreening(response.body());
                tempData.setListScreening(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Screening>> call, Throwable t) {
            }
        });

    }
    public void loadSpinnerScreening(final ArrayList<Screening> list) {
        final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item, getSreeningTime(list));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_sreening.setAdapter(adapter);
        sp_sreening.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempData.setSreeningId(list.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nDialog.dismiss();
    }
    public ArrayList<String> getSreeningTime(ArrayList<Screening> list)    {
        ArrayList<String> result = new ArrayList<>(list.size());
        for (Screening object : list) {
            result.add(object.getTime());
        }
        return result;
    }
    public void getSeatList(){
        Dataserver dataserver = APIserver.getServer();
        Call<ArrayList<Seat>> callback = dataserver.getSeat(tempData.getCinemaId());
        callback.enqueue(new Callback<ArrayList<Seat>>() {
            @Override
            public void onResponse(Call<ArrayList<Seat>> call, Response<ArrayList<Seat>> response) {
                tempData.setListSeat(response.body());
                loadSpinnerSeat(getSeatName(response.body()));
            }

            @Override
            public void onFailure(Call<ArrayList<Seat>> call, Throwable t) {

            }
        });
    }
    public ArrayList<String>  getSeatName(ArrayList<Seat> list){
        ArrayList<String> result = new ArrayList<>(list.size());
        for (Seat object : list) {
            result.add(object.getName());
        }
        return result;
    }
    public void loadSpinnerSeat(ArrayList<String>  list){
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_seat.setAdapter(adapter);
        sp_seat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               tempData.setSeatId(tempData.getListSeat().get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }





}