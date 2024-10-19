// src/components/RegistrationForm.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Button, Modal, Form } from 'react-bootstrap';

const RegistrationForm = ({ show, handleClose, fetchRegistrations, registrationId }) => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    dateOfBirth: '',
  });

  useEffect(() => {
    if (registrationId) {
      axios.get(`http://localhost:8080/api/registrations/${registrationId}`)
        .then(response => {
          setFormData(response.data);
        });
    } else {
      setFormData({
        name: '',
        email: '',
        dateOfBirth: '',
      });
    }
  }, [show, registrationId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const method = registrationId ? 'put' : 'post';
    const url = registrationId ? `http://localhost:8080/api/registrations/${registrationId}` : 'http://localhost:8080/api/registrations';
    
    axios[method](url, formData)
      .then(() => {
        fetchRegistrations();
        handleClose();
      })
      .catch(error => console.error('Error saving registration:', error));
  };

  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{registrationId ? 'Edit Registration' : 'Add Registration'}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="formName">
            <Form.Label>Name</Form.Label>
            <Form.Control
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
              required
            />
          </Form.Group>
          <Form.Group controlId="formEmail">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </Form.Group>
          <Form.Group controlId="formDateOfBirth">
            <Form.Label>Date of Birth</Form.Label>
            <Form.Control
              type="date"
              name="dateOfBirth"
              value={formData.dateOfBirth}
              onChange={handleChange}
              required
            />
          </Form.Group>
          <Button variant="primary" type="submit">
            Save
          </Button>
        </Form>
      </Modal.Body>
    </Modal>
  );
};

export default RegistrationForm;
