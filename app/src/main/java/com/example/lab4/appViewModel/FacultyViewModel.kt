package com.example.lab4.appViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.lab4.data.Faculty
import com.example.lab4.data.ListOfFaculty
import com.example.lab4.repository.MainRepository

class FacultyViewModel  : ViewModel() {
    var facultyList: MutableLiveData<ListOfFaculty?> = MutableLiveData()
    private var _faculty : Faculty? = null
    val faculty
        get() = _faculty

    private val facultyListObserver = Observer<ListOfFaculty?> { list ->
        facultyList.postValue(list)
    }

    init {
        MainRepository.getInstance().listOfFaculty.observeForever(facultyListObserver)
        MainRepository.getInstance().faculty.observeForever { _faculty = it }
    }

    fun deleteFaculty() {
        if (faculty!=null)
            MainRepository.getInstance().deleteFaculty(faculty!!)
    }

    fun appendFaculty(facultyName : String) {
        val faculty=Faculty()
        faculty.name=facultyName
        MainRepository.getInstance().updateFaculty(faculty)
    }

    fun updateFaculty(facultyName : String) {
        if (_faculty!=null) {
            _faculty!!.name = facultyName
            MainRepository.getInstance().updateFaculty(_faculty!!)
        }
    }

    fun setCurrentFaculty(faculty: Faculty) {
        MainRepository.getInstance().setCurrentFaculty(faculty)
    }

}