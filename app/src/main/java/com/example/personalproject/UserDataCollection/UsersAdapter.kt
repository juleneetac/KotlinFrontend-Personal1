package com.example.personalproject.UserDataCollection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.personalproject.R
import kotlinx.android.synthetic.main.user_row.view.*


//Esta clase es para el recyclerView
class UsersAdapter(private val users: List<UserDataCollectionItem>): RecyclerView.Adapter<UsersAdapter.ViewHolder>(){


    //Para poder hacxer click en el recyclerview
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener (listener: onItemClickListener){
        mListener = listener
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.user_row, viewGroup, false)
        return ViewHolder(view, mListener)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = users.size

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val user = users[position]
        holder.itemView.usernameView.text = user.username //se puede hacer de dos formas
        holder.email.text= user.email           //por eso el email esta distinto
        //holder.avatar.setImageResource(user.imageResource)
    }



    inner class ViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val username: TextView = itemView.usernameView
        val email: TextView = itemView.emailView
        //val avatar: ImageView = itemView.avatar

        //para poder hacer click en el recycler view
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}