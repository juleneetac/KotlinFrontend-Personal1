package com.example.personalproject.UserDataCollection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.personalproject.R
import kotlinx.android.synthetic.main.user_row.view.*

//Esta clase es para coger los datos de todos los usuarios
class UserInfo(private val users: List<UserDataCollectionItem>){

    // Return the size of your dataset (invoked by the layout manager)
    fun getItemCount() = users.size

    // Replace the contents of a view (invoked by the layout manager)
    fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val user = users[position]
        holder.username.text= user.username
        holder.email.text= user.email
        //holder.avatar.setImageResource(user.imageResource)
    }



    class ViewHolder(itemView: View){
        val username: TextView = itemView.usernameView
        val email: TextView = itemView.emailView
        //val avatar: ImageView = itemView.avatar

    }
}