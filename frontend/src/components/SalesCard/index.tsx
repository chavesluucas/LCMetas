//import do DatePicker e do CSS
import { useState } from "react"
import DatePicker from "react-datepicker"
import "react-datepicker/dist/react-datepicker.css"

import { NotificationButton } from '../NotificationButton'
import './styles.css'

export function SalesCard() {

    //Macete para criar uma data de X anos atrás (para ser de X dias, apague o vezes alguma coisa)
    const min = new Date(new Date().setDate(new Date().getDate() - (365 * 2)));

    const [minDate, setMinDate] = useState(min);
    const [maxDate, setMaxDate] = useState(new Date());


    return (
        <div className="lcmeta-card">
            <h2 className="lcmeta-sales-title">Vendas</h2>
            <div>
                <div className="lcmeta-form-control-container">
                    <DatePicker
                        selected={minDate}
                        onChange={(date: Date) => setMinDate(date)}
                        className="lcmeta-form-control"
                        dateFormat="dd/MM/yyyy"
                    />
                </div>
                <div className="lcmeta-form-control-container">
                    <DatePicker
                        selected={maxDate}
                        onChange={(date: Date) => setMaxDate(date)}
                        className="lcmeta-form-control"
                        dateFormat="dd/MM/yyyy"
                    />
                </div>
            </div>

            <div>
                <table className="lcmeta-sales-table">
                    <thead>
                        <tr>
                            <th className="show992">ID</th>
                            <th className="show576">Data</th>
                            <th>Vendedor</th>
                            <th className="show992">Visitas</th>
                            <th className="show992">Vendas</th>
                            <th>Total</th>
                            <th>Notificar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td className="show992">#341</td>
                            <td className="show576">08/07/2022</td>
                            <td>Anakin</td>
                            <td className="show992">15</td>
                            <td className="show992">11</td>
                            <td>R$ 55300.00</td>
                            <td>
                                <div className="lcmeta-red-btn-container">
                                    <NotificationButton />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td className="show992">#341</td>
                            <td className="show576">08/07/2022</td>
                            <td>Anakin</td>
                            <td className="show992">15</td>
                            <td className="show992">11</td>
                            <td>R$ 55300.00</td>
                            <td>
                                <div className="lcmeta-red-btn-container">
                                    <NotificationButton />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td className="show992">#341</td>
                            <td className="show576">08/07/2022</td>
                            <td>Anakin</td>
                            <td className="show992">15</td>
                            <td className="show992">11</td>
                            <td>R$ 55300.00</td>
                            <td>
                                <div className="lcmeta-red-btn-container">
                                    <NotificationButton />
                                </div>
                            </td>
                        </tr>
                    </tbody>

                </table>
            </div>

        </div>
    )
}