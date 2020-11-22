package com.doctor.doctorsappointment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.doctor.doctorsappointment.doctorregistration.network.DoctorRegistrationApiClient
import com.doctor.doctorsappointment.doctorregistration.network.DoctorRegistrationApiInterface
import com.doctor.doctorsappointment.doctorregistration.viewmodel.DoctorRegistrationViewModel
import com.mindorks.example.coroutines.utils.Resource
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DoctorRegistrationViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DoctorRegistrationViewModel

    private lateinit var apiHelper: DoctorRegistrationApiInterface

    @Mock
    private lateinit var apiDoctorIdObserver: Observer<Resource<String>?>

    private lateinit var mockWebServer: MockWebServer


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = DoctorRegistrationViewModel()
        viewModel.getDoctorId().observeForever(apiDoctorIdObserver)

        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiHelper = DoctorRegistrationApiClient.retrofitClient()

    }

    @Test
    fun `assert not null`() {
        Assert.assertNotNull(viewModel)
        Assert.assertNotNull(mockWebServer)
        Assert.assertNotNull(apiHelper)
    }

    @After
    fun tearDown() {
        viewModel.getDoctorId().removeObserver(apiDoctorIdObserver)
        mockWebServer.shutdown()
    }

}