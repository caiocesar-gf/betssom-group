package com.betsson.interviewtest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.betsson.interviewtest.databinding.FragmentBetListBinding
import com.betsson.interviewtest.ui.viewmodel.BetViewModel
import com.betsson.interviewtest.ui.adapter.ItemAdapter

class BetListFragment : Fragment() {

    private var _binding: FragmentBetListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BetViewModel by viewModel()

    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBetListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ItemAdapter(emptyList()) // inicia vazio
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        setObservers()
        viewModel.loadBets()

        binding.btnUpdateOdds.setOnClickListener {
            viewModel.updateOdds()
        }

    }

    private fun setObservers() {
        viewModel.bets.observe(viewLifecycleOwner) { updatedList ->
            adapter.updateList(updatedList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
