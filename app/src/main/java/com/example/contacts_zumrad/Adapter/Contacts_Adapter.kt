package com.example.contacts_zumrad.Adapter


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts_zumrad.R

class ContactAdapter(var list:MutableList<Contact>, val activity: AppCompatActivity, var contInterface: ContactInterface, var context: Context) : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    var onItemClick : ((Contact) -> Unit)? = null
    fun FilteredList(list: MutableList<Contact>){
        this.list = list
        notifyDataSetChanged()
    }
    class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.findViewById(R.id.name)
        var phone: TextView = itemView.findViewById(R.id.phone)
        var contactlayout: ConstraintLayout = itemView.findViewById(R.id.cont_lay)
        var image: ImageView = itemView.findViewById(R.id.imageView3)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        var holder = ContactHolder(LayoutInflater.from(parent.context).inflate(R.layout.contac_layout,parent,false))
        return holder
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        var item = list[position]
        holder.name.text = item.name
        holder.phone.text = item.phone
        holder.contactlayout.setOnClickListener {
            contInterface.onClick(item)
        }
        holder.image.setOnClickListener {

            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.CALL_PHONE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity, arrayOf(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                if (holder.phone.text.isNotEmpty()) {
                    val callIntent = Intent(Intent.ACTION_CALL)
                    callIntent.data = Uri.parse("tel:${holder.phone.text}")
                    activity.startActivity(callIntent)
                }
            }

        }

    }
    override fun getItemCount(): Int {
        return list.size
    }

    interface ContactInterface{
        fun onClick(contact: Contact){

        }
    }
}
