package serverConnectivity;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by saksham on 20/3/17.
 */

public class singeltonClass {


        //private static singeltonClass mInstance;
        private RequestQueue requestQueue;
        private static Context mCtx;

        public singeltonClass(Context context) {

            Log.e("","inside constructor");
            mCtx = context;
            getRequestQueue();
            Log.e("","inside constructor end");

        }


        public RequestQueue getRequestQueue() {

            if (requestQueue == null) {

                requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            }

            return requestQueue;

        }

        /*public static synchronized singeltonClass getInstance(Context context) {

            if (mInstance == null) {

                mInstance = new singeltonClass(context);

            }

            return mInstance;


        }*/

        public<T> void addToRequestQueue(Request<T> request)
        {

            requestQueue.add(request);



        }


    }

