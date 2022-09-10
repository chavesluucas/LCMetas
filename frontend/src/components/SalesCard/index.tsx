//import do DatePicker e do CSS
import axios from "axios"
import { useEffect, useState } from "react"
import DatePicker from "react-datepicker"
import "react-datepicker/dist/react-datepicker.css"
import { Sale } from "../../models/sale"
import { BASE_URL } from "../../utils/request"

import { NotificationButton } from '../NotificationButton'
import './styles.css'

export function SalesCard() {

    //Macete para criar uma data de X anos atrás (para ser de X dias, apague o vezes alguma coisa)
    const min = new Date(new Date().setDate(new Date().getDate() - (365 * 1)));

    const [minDate, setMinDate] = useState(min);
    const [maxDate, setMaxDate] = useState(new Date());

    //para armazenar a lista de sales e estamos tipando o useState para uma lista de Sale
    const [sales, setSales] = useState<Sale[]>([]);

    //React Hook - useEffect
    //useEffect( ( função ) => { ainda faz parte da função}, [ lista de dependencias])
    useEffect(() => {

        const dmin = minDate.toISOString().slice(0, 10); //slice é recortar, e queremos os 10 caracteres dps da posição 0
        const dmax = maxDate.toISOString().slice(0, 10);

        axios.get(`${BASE_URL}/sales?minDate=${dmin}&maxDate=${dmax}`)
            .then(response => {
                setSales(response.data.content); //promisses
            })
    }, [ minDate, maxDate]);

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
                        {/* Mapeie o SALES e para cara voltar q der na lista, vamos chamar o item de sale, e para cada sale renderize uma lista */}
                        {sales.map(sale => {
                            return (
                                <tr key={sale.id}>
                                    <td className="show992">{sale.id}</td>
                                    {/* Para formatar a data, botamos então que é uma nova data infornado a data do sale, e chamamos a função toLocaleDateString*/}
                                    <td className="show576">{new Date(sale.date).toLocaleDateString()}</td>
                                    <td>{sale.sellerName}</td>
                                    <td className="show992">{sale.visited}</td>
                                    <td className="show992">{sale.deals}</td>
                                    {/* Esse toFixed(2) é para informar que o numero tem duas casa decimal*/}
                                    <td>R$ {sale.amount.toFixed(2)}</td> 
                                    <td>
                                        <div className="lcmeta-red-btn-container">
                                            {/* passando parametro para o botão */}
                                            <NotificationButton saleId={sale.id} />
                                        </div>
                                    </td>
                                </tr>
                            )
                        })}
                    </tbody>

                </table>
            </div>

        </div>
    )
}