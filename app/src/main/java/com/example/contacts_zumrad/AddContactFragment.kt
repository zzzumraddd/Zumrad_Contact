package com.example.contacts_zumrad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.contacts_zumrad.Adapter.Contact
import com.example.contacts_zumrad.OtherStuff.DBHelper
import com.example.contacts_zumrad.databinding.FragmentAddContactBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddContactFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddContactBinding
            .inflate(inflater, container, false)
        var dbHelper = DBHelper(requireContext())


        binding.check.setOnClickListener {
            var name:String = binding.name.text.toString()
            var phone:String = binding.phone.text.toString()
            var contact = Contact(name = name, phone = phone)

            dbHelper.addContact(contact)
            findNavController().navigate(R.id.action_addContactFragment_to_fragmentContacts)
        }

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentContacts_to_addContactFragment)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddContactFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}