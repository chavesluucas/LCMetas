import { Header } from "./components/Header"
import { SalesCard } from "./components/SalesCard"
// yarn add react-toastify@9.0.5
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {

  return (
    <>
      <ToastContainer/>
      <Header />
      <main>
        <section id="sales">
          <div className="lcmeta-container">
            <SalesCard />
          </div>
        </section>
      </main>
    </>
  )

}

export default App
