import React, { useEffect, useState } from 'react';
import axios from 'axios';
import "./Table.css"

const Table = () => {

    const [currentIteration, setCurrentIteration] = useState(0);
    const [bestDistance, setBestDistance] = useState(0);


    const fetchData = () => {
        axios.get('http://localhost:8080/ga/currentIteration')
            .then((res) => {
                setCurrentIteration(res.data);
            });

        axios.get('http://localhost:8080/ga/bestDistance')
            .then((res) => {
                setBestDistance(res.data);
            });
    };


    useEffect(() => {
        const intervalId = setInterval(fetchData, 100);

        return () => {
            clearInterval(intervalId);
        };
    }, []);

    return (
        <div className="stat-content">
            <table>
                <thead>
                <tr className="disabled">
                    <th>Iteration</th>
                    <th>Best Distance</th>
                </tr>
            </thead>

            <tbody>
        
                <tr>
                    <td>{currentIteration == 0 ? 0 : currentIteration}</td>
                    <td>{bestDistance.toFixed(4)}</td>
                </tr>
           
            </tbody>
        </table>
        </div>
    )
}

export default Table;