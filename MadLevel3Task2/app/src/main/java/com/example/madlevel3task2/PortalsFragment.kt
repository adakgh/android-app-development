package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.*
import com.example.madlevel3task2.databinding.FragmentPortalsBinding
import androidx.browser.customtabs.CustomTabsIntent;


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {
    private var _binding: FragmentPortalsBinding? = null
    private val binding get() = _binding!!

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPortalsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager.
        binding.rvPortals.layoutManager =
                StaggeredGridLayoutManager(2, DividerItemDecoration.VERTICAL)
        binding.rvPortals.adapter = portalAdapter

        // Add a portal.
        observeAddPortalResult()

        // Add the ability to delete a portal.
        createItemTouchHelper().attachToRecyclerView(binding.rvPortals)

        // Add ability to click on the portal.
        portalAdapter.onItemClick = { portal ->
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireActivity(), Uri.parse(portal.url))
        }
    }

    // Retrieve the Portal object.
    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY_TITLE)?.let {
                val portal = it

                portals.add(portal)
                portalAdapter.notifyDataSetChanged()
            } ?: Log.e("PortalFragment", "Request triggered, but empty reminder text!")
        }
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                portals.removeAt(position)
                portalAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}