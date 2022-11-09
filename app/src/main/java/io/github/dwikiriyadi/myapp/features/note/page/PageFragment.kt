package io.github.dwikiriyadi.myapp.features.note.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.github.dwikiriyadi.myapp.core.common.gone
import io.github.dwikiriyadi.myapp.core.common.visible
import io.github.dwikiriyadi.myapp.core.viewmodels.page.PageViewModel
import io.github.dwikiriyadi.myapp.features.note.components.adapter.PageAdapter
import io.github.dwikiriyadi.myapp.R
import io.github.dwikiriyadi.myapp.databinding.FragmentPageBinding

@AndroidEntryPoint
class PageFragment : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentPageBinding
    private val viewModel by viewModels<PageViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).apply {
            title = requireArguments().getString("section_name")
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val id = requireArguments().getLong("io")
            viewModel.sectionId.postValue(id)
            recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            viewModel.getAll(id)
            viewModel.all.observe(viewLifecycleOwner, Observer { sections ->
                if (sections != null && sections.isNotEmpty()) {
                    recyclerView.adapter = PageAdapter(sections, viewModel)
                    message.gone()
                    pageContainer.visible()
                } else {
                    message.visible()
                    pageContainer.gone()
                }
            })

            addButton.setOnClickListener {
                val bundle =
                    bundleOf("event" to "add", "section_id" to id)
                findNavController().navigate(R.id.action_pageFragment_to_writeFragment, bundle)
            }
        }
    }
}
