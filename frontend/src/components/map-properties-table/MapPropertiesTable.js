import React, { useEffect, useState } from 'react';
import axios from 'axios';
import "./MapPropertiesTable.css"

const MapPropertiesTable = () => {

    const [iterationNumber, setIterationNumber] = useState(null);
    const [citiesNumber, setCitiesNumber] = useState(null);
    const [mutationPercent, setMutationPercent] = useState(null);

    const fetchData = () => {
        axios.get('http://localhost:8080/ga/iterationNumber')
            .then((res) => {
                setIterationNumber(res.data);
            });

        axios.get('http://localhost:8080/ga/citiesNumber')
            .then((res) => {
                setCitiesNumber(res.data);
            });

        axios.get('http://localhost:8080/ga/mutationPercent')
            .then((res) => {
                setMutationPercent(res.data);
        });
    };


    useEffect(() => {
        const intervalId = setInterval(fetchData, 100);

        return () => {
            clearInterval(intervalId);
        };
    }, []);

    return (
        <div className='map-properties-table-container'>
            <div className='line'>
                <p>Number of cities</p>
                <p>{citiesNumber}</p>
            </div>

            <div className='line'>
                <p>Number of iterations</p>
                <p>{iterationNumber}</p>
            </div>

            <div className='line'>
                <p>Mutation Percent</p>
                <p>{mutationPercent}</p>
            </div>

        </div>
    )
}

export default MapPropertiesTable;