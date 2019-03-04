import React from 'react';
import App from '../App';
import { shallow } from 'enzyme';
import GridData from "../GridData";

let wrapped;

beforeEach(()=>{
    wrapped = shallow(<App/>);
});

it('shows grid',()=>{
    expect(wrapped.find(GridData).length).toEqual(1);
});

it('shows grid- fail',()=>{
    expect(wrapped.find(GridData).length).toEqual(2);
});