import React from 'react';
import moxios from 'moxios';
import { mount } from 'enzyme';
import App from '../App';
import GridData from '../GridData';
import {shallow} from "enzyme/build/index";

let wrapped;
beforeEach(() => {
    moxios.install();

    moxios.stubRequest('http://localhost:8080/patentservice/api/v1/patent/all', {
        status: 200,
        response: [{documentId: 'US20010040421A1'},{documentId: 'US20010050970A1'}]
    });

    beforeEach(()=>{
        wrapped = shallow(<GridData/>);
    });
});

afterEach( () =>{
    moxios.uninstall();
});

// it('can fecth data and display', (done) => {
//     const wrapped = mount(<App> <GridData/> </App>);
//     // click fetch comments
//     //wrapped.find('.fetch-comments').simulate('click');
//     // expect and find a list of comments
//     // add tiny pause
//     // moxios.wait( () => {
//     //     wrapped.update();
//     //     expect(wrapped.find('li').length).toEqual(2);
//     //     done();
//     //     wrapped.unmount();
//     // })
// });

it('has a text area to type the search text ', () =>{
   wrapped.find('input').simulate('change',{
       target: {value: '123'}
   });

   wrapped.update();

   expect(wrapped.find('input').prop('value')).toEqual('123');

});
