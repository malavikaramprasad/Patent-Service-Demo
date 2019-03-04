import axios from 'axios';


export const fetchData = () => async dispatch  => {

        // raxios.get('https://developer.uspto.gov/ibd-api/v1/patent/application');
        const res = await axios.get('https://developer.uspto.gov/ibd-api/v1/patent/application');
        dispatch({type: "FETCH_DATA", payload: res})
        // console.log(res);
        // console.log("hi");

};

