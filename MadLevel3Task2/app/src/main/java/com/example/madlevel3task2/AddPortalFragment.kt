package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.madlevel3task2.databinding.FragmentAddPortalBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY_TITLE = "bundle_portal_title"
const val BUNDLE_PORTAL_KEY_URL = "bundle_portal_url"

class AddPortalFragment : Fragment() {
    private var _binding: FragmentAddPortalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPortalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            onAddPortal()
        }
    }

    private fun onAddPortal() {
        val titleText = binding.title.text.toString()
        val urlText = binding.url.text.toString()

        // If the fields are not filled out, give error.
        if (!titleText.isNotBlank() && urlText.equals("http://")) {
            Toast.makeText(activity, R.string.error, Toast.LENGTH_SHORT).show()
        }

        // If certain fields are not filled out, give more specific error.
        if (!titleText.isNotBlank()) {
            Toast.makeText(activity, R.string.error_title, Toast.LENGTH_SHORT).show()
        } else if (urlText.equals("http://")) {
            Toast.makeText(activity, R.string.error_url, Toast.LENGTH_SHORT).show()
        } else {
            // Set the data.
            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(BUNDLE_PORTAL_KEY_TITLE, Portal(titleText, urlText))))

            // "pop" the backstack, this means we destroy this fragment.
            findNavController().popBackStack()
        }
    }
}