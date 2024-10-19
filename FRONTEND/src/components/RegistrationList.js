// src/components/RegistrationList.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Button, Table } from 'react-bootstrap';
import RegistrationForm from './RegistrationForm';

const RegistrationList = () => {
  const [registrations, setRegistrations] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedId, setSelectedId] = useState(null);

  const fetchRegistrations = () => {
    axios.get('http://localhost:8080/api/registrations')
      .then(response => {
        setRegistrations(response.data);
      });
  };

  const handleDelete = (id) => {
    axios.delete(`http://localhost:8080/api/registrations/${id}`)
      .then(() => fetchRegistrations())
      .catch(error => console.error('Error deleting registration:', error));
  };

  const handleEdit = (id) => {
    setSelectedId(id);
    setShowModal(true);
  };

  useEffect(() => {
    fetchRegistrations();
  }, []);

  return (
    <div>
      <Button variant="primary" onClick={() => { setShowModal(true); setSelectedId(null); }}>
        Add Registration
      </Button>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Date of Birth</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {registrations.map(reg => (
            <tr key={reg.id}>
              <td>{reg.id}</td>
              <td>{reg.name}</td>
              <td>{reg.email}</td>
              <td>{reg.dateOfBirth}</td>
              <td>
                <Button variant="info" onClick={() => handleEdit(reg.id)}>Edit</Button>
                <Button variant="danger" onClick={() => handleDelete(reg.id)}>Delete</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      <RegistrationForm 
        show={showModal} 
        handleClose={() => setShowModal(false)} 
        fetchRegistrations={fetchRegistrations} 
        registrationId={selectedId} 
      />
    </div>
  );
};

export default RegistrationList;
