package id.ac.akakom.mobile.note.ui.write

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

import id.ac.akakom.mobile.note.R
import id.ac.akakom.mobile.note.databinding.FragmentWriteBinding
import id.ac.akakom.mobile.note.utility.ViewModelInjector
import id.ac.akakom.mobile.note.data.model.Page
import java.util.*

class WriteFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = WriteFragment()
    }

    private lateinit var binding: FragmentWriteBinding
    private lateinit var viewModel: WriteViewModel
    private lateinit var page: Page

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity!!.title = ""

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setHasOptionsMenu(true)

        val factory = ViewModelInjector.provideWriteViewModelFactory(requireContext())

        viewModel = ViewModelProviders.of(this, factory).get(WriteViewModel::class.java)

        binding = FragmentWriteBinding.inflate(inflater, container, false)

        viewModel.get(arguments!!.getLong("id")).observe(viewLifecycleOwner, Observer {page ->
            if (page != null) {
                this.page = page
                binding.title.setText(if (page.title != null) page.title else "")
                binding.content.setText(if (page.content != null) page.content else "")
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.write_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = Navigation.findNavController(activity!!, R.id.container)

        when(item.itemId) {
            android.R.id.home -> {
                if (arguments!!.getString("event") == "add") {
                    viewModel.insert(
                        Page(
                            title = if (binding.title.text.isNotEmpty()) binding.title.text.toString() else null,
                            content = if (binding.content.text.isNotEmpty()) binding.content.text.toString() else null,
                            section_id = arguments!!.getLong("section_id")
                        )
                    )
                } else {
                    page.title = if (binding.title.text.isNotEmpty()) binding.title.text.toString() else null
                    page.content = if (binding.content.text.isNotEmpty()) binding.content.text.toString() else null
                    page.updated_at = Calendar.getInstance()
                    viewModel.update(page)
                }
                return true
            }
            R.id.delete -> {
                viewModel.delete(page)
                navController.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
